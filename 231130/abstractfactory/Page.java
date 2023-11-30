package abstractfactory;

public abstract class Page {
	
	private Item item;
	private String title;
	private String author;
	private String content;
	
	public Item getItem() {
		return item;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getContent() {
		return content;
	}

	
	public void add() {

	}
	
	public void output() {

	}
	
	public abstract String makeHTMl();
	

}
