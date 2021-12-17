package logparser;

/**
 * @author WuChao
 * @create 2021/12/17 下午9:14
 */
public class Person {
    String name;
    String sex;
    Integer age;

    public Person(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
