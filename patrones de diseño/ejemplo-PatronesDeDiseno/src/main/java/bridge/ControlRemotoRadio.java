package bridge;

public class ControlRemotoRadio extends RemoteControl{
    public ControlRemotoRadio(Device device){
        super(device);
    }

    @Override
    public void encender(){
        device.encender();
    }

    @Override
    public void apagar(){
        device.apagar();
    }
}
