package model;

class MapDB extends Entity {


	private int			id;

	private String	key;

	private String	message;

	public MapDB() 
	{
		
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}


	public String getKey() {
		return this.key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

}
