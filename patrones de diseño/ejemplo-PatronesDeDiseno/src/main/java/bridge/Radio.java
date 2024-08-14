package bridge;

public class Radio implements Device {
    private boolean encendido = false;

    @Override
    public void encender(){
        encendido = true;
        System.out.println("la radio esta ENCENDIDO");
    }

    @Override
    public void apagar(){
        encendido = false;
        System.out.println("La radio esta APAGADA");
    }

    @Override
    public boolean estaEncendido(){
        return encendido;
    }
}
