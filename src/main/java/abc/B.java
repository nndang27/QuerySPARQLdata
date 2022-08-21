package abc;

public class B {
    public static void main(String[] args){
        int result= new A(){
            @Override
            int add(int a,int b)
            {
                return a+b;
            }
        }.add(100,200);
        System.out.println(result);
    }
}
