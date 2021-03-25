package basic;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class SingleToneSerialize {
    @Test
    public void objSaveLoadTest() throws IOException, ClassNotFoundException {
        File file = new File("SingleToneSerialize.out");
        if (!file.exists()){
            boolean cFile = file.createNewFile();
        }
        // save obj to serializable file
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        Box3 oldBox = Box3.getInstance();
        System.out.println(oldBox.toString());
        out.writeObject(oldBox);
        out.close();

        System.out.println("objLoad");
        // read serializable file
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fis);
        Object inObj = in.readObject();
        Box3 newBox = (Box3) inObj;
        in.close();
        System.out.println(newBox.toString());
        // 不相等-违背单例
        Assert.assertNotEquals(oldBox, newBox);
    }

    @Test
    public void objSaveLoadTest2() throws IOException, ClassNotFoundException {
        File file = new File("SingleToneSerialize2.out");
        if (!file.exists()){
            boolean cFile = file.createNewFile();
        }
        // save obj to serializable file
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        Box4 oldBox = Box4.getInstance();
        System.out.println(oldBox.toString());
        out.writeObject(oldBox);
        out.close();

        System.out.println("objLoad");
        // read serializable file
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fis);
        Object inObj = in.readObject();
        Box4 newBox = (Box4) inObj;
        in.close();
        System.out.println(newBox.toString());
        Assert.assertEquals(oldBox, newBox);
    }
}

class Box4 implements Serializable{
    private static final long serialVersionUID = 1L;

    static String type = "Box3";
    private int width;
    private int height;
    private transient int area;

    private static Box4 instance = new Box4(10, 20);

    private Box4(int width, int height) {
        this.width = width;
        this.height = height;
        this.area = width * height;
    }

    public static Box4 getInstance(){
        return instance;
    }

    // 用`readResolve()`中返回的对象直接替换在反序列化过程中创建的对象
    private Object readResolve() throws ObjectStreamException {
        System.out.println("readResolve");
        return instance;
    }

    @Override
    public String toString() {
        return "Child{" +
                "type=" + type +
                ", width=" + width +
                ", height=" + height +
                ", area=" + area +
                '}';
    }
}
class Box3 implements Serializable {
    private static final long serialVersionUID = 1L;

    static String type = "Box3";
    private int width;
    private int height;
    private transient int area;

    private static Box3 instance = new Box3(10, 20);

    private Box3(int width, int height) {
        this.width = width;
        this.height = height;
        this.area = width * height;
    }

    public static Box3 getInstance(){
        return instance;
    }

    @Override
    public String toString() {
        return "Child{" +
                "type=" + type +
                ", width=" + width +
                ", height=" + height +
                ", area=" + area +
                '}';
    }
}

