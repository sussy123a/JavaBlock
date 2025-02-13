import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JavaBlock extends JPanel implements KeyListener, MouseListener {
    private final int TILE_SIZE = 32; // Size of each "block"
    private final int GRID_WIDTH = 20; // Number of blocks horizontally
    private final int GRID_HEIGHT = 15; // Number of blocks vertically

    private int[][] grid; // The game world
    private int playerX = GRID_WIDTH / 2; // Player's X position in the grid
    private int playerY = GRID_HEIGHT / 2; // Player's Y position in the grid

    public JavaBlock() {
        this.grid = new int[GRID_WIDTH][GRID_HEIGHT];

        // Generate a simple world with random blocks
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = GRID_HEIGHT / 2; y < GRID_HEIGHT; y++) {
                grid[x][y] = 1; // Ground layer
            }
        }

        setPreferredSize(new Dimension(GRID_WIDTH * TILE_SIZE, GRID_HEIGHT * TILE_SIZE));
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the grid
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                if (grid[x][y] == 1) {
                    g.setColor(new Color(139, 69, 19)); // Brown for dirt
                } else {
                    g.setColor(Color.CYAN); // Sky color
                }
                g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                // Grid lines
                g.setColor(Color.BLACK);
                g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // Draw the player
        g.setColor(Color.RED);
        g.fillRect(playerX * TILE_SIZE, playerY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    // Handle key presses for player movement
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: // Move up
                if (playerY > 0 && grid[playerX][playerY - 1] == 0) {
                    playerY--;
                }
                break;
            case KeyEvent.VK_S: // Move down
                if (playerY < GRID_HEIGHT - 1 && grid[playerX][playerY + 1] == 0) {
                    playerY++;
                }
                break;
            case KeyEvent.VK_A: // Move left
                if (playerX > 0 && grid[playerX - 1][playerY] == 0) {
                    playerX--;
                }
                break;
            case KeyEvent.VK_D: // Move right
                if (playerX < GRID_WIDTH - 1 && grid[playerX + 1][playerY] == 0) {
                    playerX++;
                }
                break;
        }
        repaint();
    }

    // Handle mouse clicks to add or remove blocks
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX() / TILE_SIZE;
        int y = e.getY() / TILE_SIZE;

        if (x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                grid[x][y] = 1; // Place a block
            } else if (SwingUtilities.isRightMouseButton(e)) {
                grid[x][y] = 0; // Remove a block
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaBlock");
        JavaBlock game = new JavaBlock();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
