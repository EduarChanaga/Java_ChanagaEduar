package bridge;

public abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public abstract void encender();
    public abstract void apagar();
}
