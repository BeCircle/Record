class Client{
    public static void main(String[] args) {
        // static proxy
        ISubject sub = new SubStaticProxy(new RealSubject());
        System.out.println(sub.request());

        // dynamic proxy
        //加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        ISubject dySub = (ISubject) new SubDynamicProxy().bind(new RealSubject());
        System.out.println(dySub.request());
    }
}