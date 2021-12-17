package logparser;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Test;

import java.io.*;

/**
 * @author WuChao
 * @create 2021/12/17 下午7:26
 */
public class WriterFileTest {
    // 不同方式写文件大小差异
    @Test
    public void test()throws Exception {
        String fileName = "src/main/resources/output/DataOutput2.txt";
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            content.append("ADGYUFDUBKGYJF\n");
        }
        content.append("END\n");
        method1(fileName,content.toString());
    }

    public void method1(String filePath, String content)throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        DataOutputStream out=new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(file)));
//        out.writeInt(10);
//        System.out.println(out.size()+" bytes have been written.");
//        out.writeDouble(31.2);
//        System.out.println(out.size()+" bytes have been written.");
//        out.writeBytes("JAVA");
        out.writeBytes(content);
        System.out.println(out.size()+" bytes have been written.");
        out.close();

//        stream.writeBytes(content); // 最小


    }

    public void method2(String filePath, String content)throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file, false);
        writer.write(content);
        writer.close();
    }

    public void method3(String filePath, String content)throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,false));
        writer.write(content);
        writer.close();
    }


    @Test
    public void  writer()throws Exception {
        // 直接全部采用 kryo 进行序列化存储
        Kryo kryo = new Kryo();
        kryo.register(Person.class);
        File file = new File("src/main/resources/output/persons.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        Person p1 = new Person("aa", "m", 12);
        Person p2 = new Person("bb", "f", 15);
        Person p3 = new Person("cc", "f", 18);
        Person p4 = new Person("dd", "m", 20);
        Person p5 = new Person("ee", "f", 29);
        Output output = new Output(new FileOutputStream(file,true));
        // 共10 组数据
        kryo.writeObject(output, 10);
        kryo.writeObject(output, 5);
        kryo.writeObject(output,p1);
        kryo.writeObject(output,p2);
        kryo.writeObject(output,p3);
        kryo.writeObject(output,p4);
        kryo.writeObject(output,p5);

        output.close();



    }

    @Test
    public void reader()throws Exception {
        File file = new File("src/main/resources/output/persons.dat");
        if (!file.exists()) {
            return;
        }
        Input input = new Input(new FileInputStream(file));
        Kryo kryo = new Kryo();
        kryo.register(Person.class);
        int count = kryo.readObject(input, Integer.class);
        System.out.println("总组数:" + count);
        int persons = kryo.readObject(input, Integer.class);
        System.out.println("人数：" + persons);
        for (int i = 0; i < persons; i++) {
            Person p = kryo.readObject(input, Person.class);
            System.out.println(p);
        }
        input.close();
    }




}