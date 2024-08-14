/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

public class Demo {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);

        if (hole.fits(rpeg)){
            System.out.println("La clavija redonda r5 se adapta al agujero redondo r5");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);

        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);

        if (hole.fits(smallSqPegAdapter)){
            System.out.println("Clavija cuadrada w2 que se adapta al orificio redondo r5");
        }

        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("La clavija cuadrada w20 no encaja en el orificio redondo r5");
        }
    }
}
