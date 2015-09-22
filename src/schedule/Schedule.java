package schedule;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.CourseData;

public class Schedule {

	private CourseData data;

	public Schedule() throws ClassNotFoundException, IOException {
		readData();
	}

	private CourseData readData() throws IOException, ClassNotFoundException {
		CourseData data = null;

		FileInputStream fileIn = new FileInputStream("/CourseData/data.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		data = (CourseData) in.readObject();
		in.close();
		fileIn.close();

		return data;
	}

	public void saveData() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("/CourseData/data.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(data);
		out.close();
		fileOut.close();
	}





}
