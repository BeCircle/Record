package basic;

import org.junit.Test;

import java.io.*;

public class ExternalizableTest {
    private void objSave(File file) throws IOException {
        // save obj to serializable file
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        Box2 oldBox = new Box2(10, 20);
        System.out.println(oldBox.toString());
        out.writeObject(oldBox);
        out.close();
    }

    private void objLoad(File file) throws IOException, ClassNotFoundException {
        // read serializable file
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fis);
        Object inObj = in.readObject();
        Box2 newBox = (Box2) inObj;
        in.close();
        System.out.println(newBox.toString());
    }
    @Test
    public void objSaveRepTest() throws IOException, ClassNotFoundException {
        // 重复保存对象，后面都是保存引用
        File file = new File("externalizableTest.out");
        if (!file.exists()){
            boolean cFile = file.createNewFile();
        }
        new Box2();
        objSave(file);
        System.out.println("objLoad");
        objLoad(file);
    }
}

class Box2 implements Externalizable {
    private static final long serialVersionUID = 1L;

    static  String type = "Box";
    private int width;
    private int height;
    private transient int area;

    public Box2(int width, int height) {
        this.width = width;
        this.height = height;
        this.area = width * height;
    }

    public Box2() {
        System.out.println("no param constructor");
    }

    public void setWidth(int width) {
        this.width = width;
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("write external");
        out.writeInt(width);
        out.writeInt(height);
    }

    // 用`readResolve()`中返回的对象直接替换在反序列化过程中创建的对象
    // readExternal 之后调用
//    private Object readResolve() throws ObjectStreamException {
//        System.out.println("readResolve");
//        return new Box2();
//    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("read external");
        width = in.readInt();
        height = in.readInt();
    }
}
