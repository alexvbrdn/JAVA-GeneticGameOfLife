import javax.swing.JFrame;


public class Window extends JFrame {
	private Panel pan;
	public Window(World world) {
		pan = new Panel(world);
		this.setTitle("GGoL");
	    this.setSize(950, 830);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(pan);
	    this.setVisible(true);
	}
	public void render(){
		pan.revalidate();
		pan.repaint();
	}

}
