package bridge;

// implementacion concreta de un dispositivo: Televisor
public class Televisor implements Device {
    private boolean encendido = false;

    @Override
    public void encender(){
        encendido = true;
        System.out.println("El televisor esta ENCENDIDO");
    }

    @Override
    public void apagar(){
        encendido = false;
        System.out.println("El televisor esta APAGADO");
    }

    @Override
    public boolean estaEncendido(){
        return encendido;
    }
}

