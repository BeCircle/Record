class SubStaticProxy implements ISubject{
    private RealSubject reaSubject;

    public SubStaticProxy(RealSubject reaSubject) {
        this.reaSubject = reaSubject;
    }

    @Override
    public String request() {
        return "before request.\n"+reaSubject.request()+"\nAfter request.";
    }
}