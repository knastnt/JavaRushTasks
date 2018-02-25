package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.ImageReader;
import com.javarush.task.task16.task1631.common.ImageTypes;

public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes imageTypes){
        try {
            String className = imageTypes.toString().substring(0, 1).toUpperCase() + imageTypes.toString().substring(1).toLowerCase() + "Reader";
            //System.out.println(Solution.class.getPackage().getName() + ".common." + className);
            Class c = Class.forName(Solution.class.getPackage().getName() + ".common." + className);
            return (ImageReader)c.newInstance();
        }catch(Exception e){
            //System.out.println(e.getClass());
            throw new IllegalArgumentException();
        }

    }
}
