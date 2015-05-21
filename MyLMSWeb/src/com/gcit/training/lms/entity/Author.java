package com.gcit.training.lms.entity;

//import java.lang.reflect.Method;

public class Author implements Entity{

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authorId;
		result = prime * result
				+ ((authorName == null) ? 0 : authorName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (authorId != other.authorId)
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		return true;
	}

	private int authorId;
	private String authorName;

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/*@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			Author a = new Author();
			a.setAuthorName("test");

			System.out.println(a.getAuthorName());

			String className = "com.gcit.training.lms.entity.Author";
			String attrib = "authorName";

			// Author a = new Author();
			@SuppressWarnings("rawtypes")
			Class c = Class.forName(className);
			Object obj = c.newInstance();

			//a.setAuthorName("test");
			Method setter = c.getMethod(
					"set" + attrib.substring(0, 1).toUpperCase()
							+ attrib.substring(1, attrib.length()),
					String.class);
			setter.invoke(obj, "test");

			//System.out.println(a.getAuthorName());
			Method getter = c.getMethod(
					"get" + attrib.substring(0, 1).toUpperCase()
							+ attrib.substring(1, attrib.length()),
					String.class);
			System.out.println(getter.invoke(obj));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
