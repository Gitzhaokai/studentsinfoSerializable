package day02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//Homework
public class StudentSerializableTest {

	private static Scanner scanner = new Scanner(System.in);
	private static File file = new File("src/day02/students.txt");

	private static void addStudentsInfo(List<Student> list) {
		while (true) {
			System.out.println("请输入学生的姓名:");
			String name = scanner.next();
			System.out.println("请输入学生的性别:");
			String sex = scanner.next();
			System.out.println("请输入学生的年龄:");
			int age = scanner.nextInt();
			Student student = new Student(name, sex, age);
			list.add(student);
			System.out.println("是否继续？1-继续 2-退出");
			int type1 = scanner.nextInt();
			if (type1 != 1) {
				break;
			}
		}
	}

	private static void writeStudentsInfo(List<Student> list) {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static List<Student> readStudentsInfo(List<Student> list) {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			if (file.exists()) {
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				list = (List<Student>) objectInputStream.readObject();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();
		while (true) {
			System.out
					.println("===请输入要进行的操作:===\n1-录入\n2-展示\n3-删除\n4-查询\n0-退出");
			int type = scanner.nextInt();
			if (type == 0) {
				break;
			}
			switch (type) {
			case 1:
				if (file.exists()) {
					list = readStudentsInfo(list);
				}
				addStudentsInfo(list);
				writeStudentsInfo(list);
				break;
			case 2:
				list = readStudentsInfo(list);
				for (Student student : list) {
					System.out.println(student);
				}
				break;
			case 3:
				list = readStudentsInfo(list);
				System.out.println("请输入想要删除的学生姓名:");
				String name = scanner.next();
				Iterator<Student> iterator = list.iterator();
				while (iterator.hasNext()) {
					if (iterator.next().getName().equals(name)) {
						iterator.remove();
					}
				}
				writeStudentsInfo(list);
				break;
			case 4:
				list = readStudentsInfo(list);
				System.out.println("请输入要查询的学生姓名:");
				String name1 = scanner.next();
				for (Student student : list) {
					if (name1.equals(student.getName())) {
						System.out.println(student);
					}
				}
				break;
			default:
				;
			}
		}
	}
}
