import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class GSONTest {

	public static void main(String[] args) {
		
		Gson gson = new Gson();
		String jsonStr;
		
		
		/* Map객체 - json문자열 */
		
		// map객체를 json과 연동할 때 key는 String으로 받아야함
		Map<String,String> inputMap
			= new HashMap<String,String>();
		inputMap.put("name","홍길동");
		inputMap.put("age","30");
		inputMap.put("address","서울시 어딘가");
		
		// Map객체를 json문자열로 (직렬화)
		jsonStr = gson.toJson(inputMap);
		System.out.println(jsonStr);
		
		// json문자열을 Map객체로 (역직렬화)
		Map jsonMap = gson.fromJson(jsonStr, Map.class);
		System.out.println(jsonMap.get("address"));
		
		
		/* List객체 - json문자열 */
		
		List<String> strList = new ArrayList<String>();
		strList.add("한국");
		strList.add("일본");
		strList.add("중국");
		
		// List객체를 json문자열로 (직렬화)
		jsonStr = gson.toJson(strList);
		System.out.println(jsonStr);
		
		// json문자열을 List객체로 (역직렬화)
		List jsonList = gson.fromJson(jsonStr, List.class);
		System.out.println(jsonList.get(1));
		
		
		/* Class객체 - json문자열 */
		
		Person person1 = new Person("너구리",new Integer(2), "숲 속 어딘가");
		
		// person객체를 json문자열로 (직렬화)
		jsonStr = gson.toJson(person1);
		System.out.println(jsonStr);
		
		// json문자열을 person객체로 (역직렬화)
		Person person2 = gson.fromJson(jsonStr, Person.class);
		System.out.println(person2);
		
		
		/* # 실습1. List에 Person을 3명 담고 직렬화/역직렬화 */
		
		List<Person> personList = new ArrayList<Person>();
		personList.add(new Person("감자", new Integer(4), "밭"));
		personList.add(new Person("배추", new Integer(3), "농장"));
		personList.add(new Person("인삼", new Integer(20), "숲 속"));
		
		jsonStr = gson.toJson(personList);
		System.out.println(jsonStr);
		
		List rePersonList = gson.fromJson(jsonStr, List.class);
		System.out.println(rePersonList.get(0));
		
		
		/* # 실습2. Map에 Person을 3명 담고 직렬화/역직렬화 */
		
		Map<String, Person> personMap = new HashMap<String, Person>();
		personMap.put("첫 째",new Person("일순", new Integer(32), "장남"));
		personMap.put("둘 째",new Person("이순", new Integer(30), "차남"));
		personMap.put("셋 째",new Person("삼순", new Integer(22), "막내"));
		
		jsonStr = gson.toJson(personMap);
		System.out.println(jsonStr);
		
		Map rePersonMap = gson.fromJson(jsonStr, Map.class);
		System.out.println(rePersonMap.get("둘 째"));
		
		
		/* GSON의 JsonObject (jar 파일을 연동해야 사용할 수 있음) */
		
		// JsonObject에 property를 추가해서 만든 객체
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "토끼");
		jsonObject.addProperty("age", 3);
		jsonObject.addProperty("alive", true);
		
		System.out.println(jsonObject);
		// 각 key의 value type별로 출력 가능
		System.out.println(jsonObject.get("name").getAsString());
		System.out.println(jsonObject.get("age").getAsInt());
		System.out.println(jsonObject.get("alive").getAsBoolean());
		
		
		// GSON pretty printing -> gson을 보기 좋게 출력해주는 메소드
		Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
		String prettyStr = gson2.toJson(jsonObject);
		System.out.println(prettyStr);
		
		} // main
} // class
