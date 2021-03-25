package java8Features.Lambda;

public class ProductB implements Product{
    // 接口的默认方法可继承，可覆盖
    @Override
    public String desc(){
        return "T公司的B产品";
    }
}
