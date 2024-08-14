package Patronesestructurales.bridge;

public class ControlRemotoTelevisor extends RemoteControl{
    public ControlRemotoTelevisor(Device device){
        super(device);
    }

    @Override
    public void encender(){
        device.encender();
    }

    @Override
    public void apagar() {
        device.apagar();
    }
}
