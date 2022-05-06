package com.company.Furniture.services;

import java.io.*;
import java.util.stream.Collectors;

public class InputStreamServiceImpl implements InputStreamService {

    @Override
    public String openInputStream(File file){
        try {
            InputStream inputStream = new FileInputStream(file);
            System.out.println(inputStream);
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
        }catch (IOException e){
            // TODO: add log ?
            return InputStream.nullInputStream().toString();
        }
    }

}
