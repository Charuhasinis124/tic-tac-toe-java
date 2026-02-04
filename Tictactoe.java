import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tictactoe extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    String turn = "X";
    boolean gameOver = false;

    Tictactoe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameOver) return;

        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return;
        }

        clicked.setText(turn);

        if (checkWinner()) {
            JOptionPane.showMessageDialog(this, "Player " + turn + " wins!");
            gameOver = true;
            resetGame();
            return;
        }

        if (isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            gameOver = true;
            resetGame();
            return;
        }

        // Change turn
        turn = turn.equals("X") ? "O" : "X";
    }

    boolean checkWinner() {
        int[][] winPatterns = {
            {0,1,2}, {3,4,5}, {6,7,8}, // rows
            {0,3,6}, {1,4,7}, {2,5,8}, // columns
            {0,4,8}, {2,4,6}           // diagonals
        };

        for (int[] pattern : winPatterns) {
            if (buttons[pattern[0]].getText().equals(turn) &&
                buttons[pattern[1]].getText().equals(turn) &&
                buttons[pattern[2]].getText().equals(turn)) {
                return true;
            }
        }
        return false;
    }

    boolean isDraw() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    void resetGame() {
        int option = JOptionPane.showConfirmDialog(
                this,
                "Play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
            for (JButton b : buttons) {
                b.setText("");
            }
            turn = "X";
            gameOver = false;
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Tictactoe();
    }
}
