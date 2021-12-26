package week4.Arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

public class Member {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {

		/*List<MemberDTO> list = Arrays.asList(
				new MemberDTO(UUID.randomUUID(), "?κΈΈλ")
				, new MemberDTO(UUID.randomUUID(), "κ°κ°μ°?")
				, new MemberDTO(UUID.randomUUID(), "? κ΄??")
				, new MemberDTO(UUID.randomUUID(), "?΄?? ")
				, new MemberDTO(UUID.randomUUID(), "?₯??€")
		);*/
		
		ArrayList<MemberDTO> list = new ArrayList<>();
		list.add(new MemberDTO(UUID.randomUUID(), "?κΈΈλ"));
		list.add(new MemberDTO(UUID.randomUUID(), "κ°κ°μ°?"));
		list.add(new MemberDTO(UUID.randomUUID(), "? κ΄??"));
		list.add(new MemberDTO(UUID.randomUUID(), "?΄?? "));
		list.add(new MemberDTO(UUID.randomUUID(), "?₯??€"));
					
		/*List<ArrayList<MemberDTO>> stream = Arrays.asList(list);
		stream.stream().forEach(i -> i.forEach(
								j -> System.out.println("[key] : " + j.getKey() + " [name] : " + j.getName())));
		System.out.println();*/
	
		System.out.println("1. ? μ²? μ‘°ν | 2. ?? μ‘°ν | 3. ?? μΆκ? | 4. ?? κ°±μ  | 5. ?? ?­? ");
		int N = input_check(br.readLine());
		
		// λΉμ¦??€ λ‘μ§
		service(N, list);

		System.out.println("?λΉμ€κ°? μ’λ£???΅??€.");
	}

	// ?μΆλ ₯ κ°? κ²?μ¦?
	private static int input_check(String str) {
		int N = 0;
		
		try {
			N = Integer.parseInt(str);

		} catch(NumberFormatException e) {
			System.out.println("? ?¨?μ§? ??? ?? ₯???€.");
		}
		return N;
	}


	// λΉμ¦??€ λ‘μ§ κ΄?? ¨ λ©μ?
	private static void service(int N, ArrayList<MemberDTO> list) throws IOException {		
		// ? μ²? ?? ? λ³? μ‘°ν
		if(N == 1) {
			if(list.size() == 0) {
				System.out.println("?±λ‘λ ?? ? λ³΄κ? ??΅??€.");
			}
			else {				
				list.stream().forEach(a -> System.out.println("[key] : " + a.getKey() + " [name] : " + a.getName()));
			}	
		}
		
		// keyλ‘? ?? ? λ³? μ‘°ν
		else if(N == 2) {
			System.out.println("μ‘°ν?  ?? ID κ°μ ?? ₯?΄μ£ΌμΈ?.");
			String str = br.readLine();
			
			if(list.size() == 0) {
				System.out.println("?±λ‘λ ?? ? λ³΄κ? ??΅??€.");
			}
			else {
				try {
					String member = list.stream()
							.filter(m -> m.getKey().equals(UUID.fromString(str)))
							.findAny().get().getName();
					System.out.println("??λͺ? : " + member);		
				} catch(IllegalArgumentException e) {
					System.out.println("? ?¨?μ§? ??? ID κ°? ???€.");
				} catch(NoSuchElementException e) {
					System.out.println("μ‘°ν? ? λ³΄κ? ??΅??€.");
				}
			}		
		}

		// ?? ? λ³? μΆκ?
		else if(N == 3) {
			System.out.println("?λ‘? ?±λ‘ν  ??λͺμ ?? ₯?΄μ£ΌμΈ?.");
			String str = br.readLine();
			
			// λ°©λ²1
			// list.add(new MemberDTO(UUID.randomUUID(), str));
		
			ArrayList<MemberDTO> list2 = new ArrayList<>(Arrays.asList(new MemberDTO(UUID.randomUUID(), str)));			
			
			// λ°©λ²2
			/*list = (ArrayList<MemberDTO>) Stream.of(list, list2)
					.flatMap(x -> x.stream()).collect(Collectors.toList());*/
			
			// λ°©λ²3
			list = (ArrayList<MemberDTO>) Stream.concat(list.stream(), list2.stream())
					.collect(Collectors.toList());
			
			// λ°©λ²3_2
			/*list = (ArrayList<MemberDTO>) Stream.concat(list.stream(), Arrays.asList(new MemberDTO(UUID.randomUUID(), str)).stream())
					.collect(Collectors.toList());*/
			
			System.out.println("?? ?±λ‘μ΄ ?λ£λ??΅??€.");
		}
		
		// key ?? ? λ³? κ°±μ 
		else if(N == 4) {
			System.out.println("κ°±μ ?  ?? ID κ°μ ?? ₯?΄μ£ΌμΈ?.");
			String str = br.readLine();
			
			if(list.size() == 0) {
				System.out.println("?±λ‘λ ?? ? λ³΄κ? ??΅??€.");
			}
			else {
				try {
					// κ°? μ‘°ν
					list.stream()
					.filter(m -> m.getKey().equals(UUID.fromString(str)))
					.findAny().get().getName();
					
					System.out.println("λ³?κ²½ν  ??λͺμ ?? ₯?΄μ£ΌμΈ?.");
					String str2 = br.readLine();
						
					// κ°? κ°±μ  
					
					// λ°©λ²1
					/*list.stream().filter(m -> m.getKey().equals(UUID.fromString(str)))
					.findAny().get().setName(str2);*/
					
					// λ°©λ²2
					list.stream().filter(m -> m.getKey().equals(UUID.fromString(str)))
					.forEach(m -> m.setName(str2));
						
					System.out.println(str2 + "(?Ό)λ‘? ??λͺμ΄ λ³?κ²½λ??΅??€.");		
					
				} catch(IllegalArgumentException e) {
					System.out.println("? ?¨?μ§? ??? ID κ°? ???€.");
				} catch(NoSuchElementException e) {
					System.out.println("μ‘°ν? ? λ³΄κ? ??΅??€.");
				}
			}
		}

		// key ?? ? λ³? ?­? 
		else if(N == 5) {
			System.out.println("?­? ?  ?? ID κ°μ ?? ₯?΄μ£ΌμΈ?.");
			String str = br.readLine();		
			
			try {
				String member = list.stream()
						.filter(m -> m.getKey().equals(UUID.fromString(str)))
						.findAny().get().getName();
				
				// κ°? ? κ±?
				/*list.stream().filter(m -> m.getKey().equals(UUID.fromString(str)))
				.collect(Collectors.toList()).forEach(a -> list.remove(a));*/
				
				// κ°μ  : java8 ?΄? collection? μΆκ?? removeIf ?¨?
				list.removeIf(m -> m.getKey().equals(UUID.fromString(str)));
				
				System.out.println(member + "?? ?? ? λ³΄κ? ?­? ???΅??€.");		
			} catch(IllegalArgumentException e) {
				System.out.println("? ?¨?μ§? ??? ID κ°? ???€.");
			} catch(NoSuchElementException e) {
				System.out.println("μ‘°ν? ? λ³΄κ? ??΅??€.");
			}
	
		}
		
		String temp = br.readLine();
		if(!temp.isEmpty()) {
			try {
				N = Integer.parseInt(temp);
				service(N, list);
			} catch(NumberFormatException e) {
				System.out.println("? ?¨?μ§? ??? ?? ₯???€.");
				temp = br.readLine();
				service(N, list);
			}
			
		}
		
	}

}
