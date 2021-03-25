package basic;

import org.junit.Test;

import java.io.*;

public class SerializableTest {

    private void objSave(File file) throws IOException {
        // save obj to serializable file
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        Box oldBox = new Box(10, 20);
        System.out.println(oldBox.toString());
        out.writeObject(oldBox);
        out.close();
    }

    private void objLoad(File file) throws IOException, ClassNotFoundException {
        // read serializable file
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fis);
        Object inObj = in.readObject();
        Box newBox = (Box) inObj;
        in.close();
        System.out.println(newBox.toString());
    }

    private void objSaveRep(File file) throws IOException {
        // 重复保存对象
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        Box oldBox = new Box(10, 20);
        System.out.println(oldBox.toString());
        // 重复保存
        for (int i = 0; i < 5; i++) {
            out.writeObject(oldBox);
        }
        // 修改对象属性，再保存,任然只保存引用，修改没有得到保存
        oldBox.setWidth(30);
        System.out.println(oldBox.toString());
        out.writeObject(oldBox);

        out.close();
    }

    private void objLoadRep(File file) throws IOException, ClassNotFoundException {
        // 读取重复的对象
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fis);
        for (int i = 0; i < 6; i++) {
            Box newBox = (Box) in.readObject();
            System.out.println(newBox.toString());
        }
        in.close();
    }

    @Test
    public void objSaveRepTest() throws IOException, ClassNotFoundException {
        // 重复保存对象，后面都是保存引用
        File file = new File("serializableTest.out");
        if (!file.exists()){
            boolean cFile = file.createNewFile();
        }
        objSaveRep(file);
        System.out.println("objLoadRep");
        objLoadRep(file);
    }

    @Test
    public void loadSerializableFile() throws IOException, ClassNotFoundException {
        // 保存序列化文件后，修改类的 serialVersionUID，加载不成功
        File file = new File("serializableTest.out");
        objLoad(file);
    }

    @Test
    public  void testSerializable() throws IOException, ClassNotFoundException {
        File file = new File("serializableTest.out");
        if (!file.exists()){
            boolean cFile = file.createNewFile();
        }
        objSave(file);
        objLoad(file);
    }
}

class Box implements Serializable {
    private static final long serialVersionUID = 1L;

    static  String type = "Box";
    private int width;
    private int height;
    private transient int area;

    public Box(int width, int height) {
        this.width = width;
        this.height = height;
        this.area = width * height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("writeObject");
        out.defaultWriteObject();
        out.writeInt(area);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("readObject");
        in.defaultReadObject();
        area = in.readInt();
    }

    // 用`readResolve()`中返回的对象直接替换在反序列化过程中创建的对象
    // readObject 之后调用
//    private Object readResolve() throws ObjectStreamException {
//        System.out.println("readResolve");
//        return new Box(-1, -1);
//    }


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

