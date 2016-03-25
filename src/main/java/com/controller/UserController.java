package com.controller;

import com.dto.UserDto;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * Class {@link UserController}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 23.03.16
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @return login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showForm() {
        return "login";
    }

    /**
     * @param userDto
     * @return file
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute UserDto userDto) {
        User user = userService.findByLogin(userDto.getLogin());
        if (user == null) {
            return "redirect:/user/login";
        }
        return "file";
    }

    /**
     * @param file
     * @param model
     * @return result
     * @throws IOException
     */
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String workFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        List<String> str = working(convert(file));
//        for (String i : str) {
//            String temp = i;
//            String   tmpString = temp.replace(' ', "&nbsp;");
//            str.
//
//        }
        model.addAttribute("str", str);
        return "result";
    }

    /**
     * @return zip
     */
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String workFileToZip() {
        writeToZip();
        return "zip";
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public List<String> working(File file) throws FileNotFoundException {
        String myArray[] = read(file).split("\n");
        String st = "";
        List<String> str = new ArrayList<String>();
        for (int k = 0; k < myArray.length; k++) {
            st = myArray[k];
            String st1 = "";
            Queue queue = new PriorityQueue();
            char[] array = st.toCharArray();
            char[] array2 = new char[array.length];
            for (int i = 0; i < array2.length; i++) {
                array2[i] = 32;
            }
            for (int i = 0; i < array.length; i++) {
                if (array[i] != '(' && array[i] != ')') {
                    continue;
                }
                if (array[i] == '(') {
                    queue.add(array[i]);
                    array2[i] = 94;
                    continue;
                }
                if (array[i] == ')') {
                    queue.remove();
                    for (int j = i; j >= 0; j--) {
                        if (array[j] == '(' && array2[j] != ' ') {
                            array2[j] = 32;
                            break;
                        }
                    }
                }
            }
            if (queue.isEmpty()) {
            } else {
                write(st);
                str.add(st);
                st1 = new String(array2);
                write(st1);
                str.add(st1);
            }
        }
        return str;
    }

    public static String read(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                //считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //закрываем файл
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Возвращаем полученный текст с файла
        return sb.toString();
    }

    public static void write(String text) {
        //Определяем файл
        File file = new File("out.txt");
        try {
            //проверяем, что если файл не существует - то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            try {
                //Записываем текст в файл
                fileWriter.write(text);
                fileWriter.append('\n');
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToZip() {
        String filename = "out.txt";
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("out.zip"));
             FileInputStream fis = new FileInputStream(filename)) {
            ZipEntry entry = new ZipEntry(filename);
            zout.putNextEntry(entry);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

}
