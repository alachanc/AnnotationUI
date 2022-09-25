package com.example.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectLoader
{
    public static final String FILENAME = "cool_file.tmp";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String test = "This will work if the objects were written with a single ObjectOutputStream. " +
                "If several ObjectOutputStreams were used to write to the same file in succession, " +
                "it will not. – Tom Anderson 4 mins ago";

        try (FileOutputStream fos = new FileOutputStream(FILENAME))
        {
            for (String s : test.split("\\s+"))
            {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(s);
            }
        }

        List<Object> results = new ArrayList<Object>();

        try (FileInputStream fis = new FileInputStream(FILENAME))
        {
            while (true)
            {
                ObjectInputStream ois = new ObjectInputStream(fis);
                results.add(ois.readObject());
            }
        } catch (EOFException ignored)
        {
            // as expected
        }
        System.out.println("results = " + results);
    }
}
