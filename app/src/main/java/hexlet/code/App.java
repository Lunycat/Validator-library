package hexlet.code;

public class App {
    public static void main(String[] args) {
        Validator v = new Validator();
        var s = v.number();
        System.out.println(s.isValid(null));
    }
}
