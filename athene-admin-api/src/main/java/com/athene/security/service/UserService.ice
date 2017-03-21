[["java:package:com.athene.security"]]
module service {
	
	["java:serializable:com.athene.security.domain.User"]
	sequence<byte> User;
	
	["java:serializable:org.springframework.data.domain.Page"]
	sequence<byte> Page;
	
	sequence<string> UserIds;
	
	interface UserService {
		
		User save(User user);
		
		void deleteUsers(UserIds ids);
		
		User getUser(string key);
		
		Page getUsers(string queryKey);
	};
};