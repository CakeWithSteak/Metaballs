import java.util.LinkedList;
import java.util.List;
import blob.Blob;
import processing.core.PApplet;
import processing.core.PMatrix3D;

public class MetaballRenderer extends PApplet {
    private List<Blob> blobs = new LinkedList<>();
    private int width = 100;
    private int height = 100;
    private int depth = 100;

    private final float threshold = 1f;
    private PMatrix3D transform = new PMatrix3D(
            5,0,0,200,
            0,5,0,200,
            0,0,5,0,
            0,0,0,1
    );


    private float[][][] calculateField() {
        float res[][][] = new float[width][height][depth];
        for(int x = 0;x < width;++x) {
            for(int y = 0;y < height;++y) {
                for(int z = 0;z < depth;++z) {
                    for (Blob blob : blobs)
                        res[x][y][z] += blob.getStrengthAtPoint(x, y, z);
                }
            }
        }
        return res;
    }

    @Override
    public void draw() {
        background(0);

        /*translate(200,200);
        scale(5);*/
        applyMatrix(transform);
        beginShape(TRIANGLES);
        noStroke();
        //fill(127,0,127);

        float[][][] field = calculateField();
        for(int x = 0;x < width;++x) {
            for (int y = 0; y < height; ++y) {
                for (int z = 0; z < depth; ++z) {
                    if(field[x][y][z] < threshold)
                        continue;

                    if(x + 1 != width && field[floor(x + 1)][y][z] < threshold) {
                        fill(127,0,127);
                        vertex(x+1,y,z);
                        vertex(x+1,y+1,z);
                        vertex(x+1,y,z+1);

                        vertex(x+1,y,z+1);
                        vertex(x+1,y+1,z);
                        vertex(x+1,y+1,z+1);
                    }

                    if(x != 0 && field[x - 1][y][z] < threshold) {
                        fill(0,127,0);
                        vertex(x-1,y,z);
                        vertex(x-1,y-1,z);
                        vertex(x-1,y,z-1);

                        vertex(x-1,y,z-1);
                        vertex(x-1,y-1,z);
                        vertex(x-1,y-1,z-1);
                    }

                    if(y + 1 != height && field[x][y + 1][z] < threshold) {
                        fill(90,10,127);
                        vertex(x,y+1,z);
                        vertex(x+1,y+1,z);
                        vertex(x,y+1,z+1);

                        vertex(x+1,y+1,z);
                        vertex(x,y+1,z+1);
                        vertex(x+1,y+1,z+1);
                    }

                    if(y != 0 && field[x][y - 1][z] < threshold) {
                        fill(40,12,43);
                        vertex(x,y-1,z);
                        vertex(x-1,y-1,z);
                        vertex(x,y-1,z-1);

                        vertex(x-1,y-1,z);
                        vertex(x,y-1,z-1);
                        vertex(x-1,y-1,z-1);
                    }

                    if(z + 1 != depth && field[x][y][z + 1] < threshold) {
                        fill(43,10,220);
                        vertex(x,y,z+1);
                        vertex(x,y+1,z+1);
                        vertex(x+1,y,z+1);

                        vertex(x,y+1,z+1);
                        vertex(x+1,y,z+1);
                        vertex(x+1,y+1,z+1);
                    }

                    if(z != 0 && field[x][y][z - 1] < threshold) {
                        fill(50,0,50);
                        vertex(x,y,z-1);
                        vertex(x,y-1,z-1);
                        vertex(x-1,y,z-1);

                        vertex(x,y-1,z-1);
                        vertex(x-1,y,z-1);
                        vertex(x-1,y+1,z-1);
                    }
                }
            }
        }
        endShape();
        update();
    }

    private void update() {
        for (Blob blob : blobs)
            blob.update(width, height, depth);
    }

    @Override
    public void settings() {
        size(1000, 1000, P3D);
    }
    public static void main(String[] args) {
        PApplet.main(MetaballRenderer.class);
    }

    @Override
    public void setup() {
        Blob a = new Blob(5,5,5,200);
        a.vX = 0.0f;
        a.vY = 0.4f;
        a.vZ = 0.1f;
        blobs.add(a);

        Blob b = new Blob(30,30,0,130);
        b.vX = 0.1f;
        b.vY = -0.4f;
        b.vZ = 0.0f;
        blobs.add(b);

        Blob c = new Blob(30,30,0,130);
        c.vX = -0.3f;
        c.vY = 0.1f;
        c.vZ = 0.2f;
        blobs.add(c);

    }

}
