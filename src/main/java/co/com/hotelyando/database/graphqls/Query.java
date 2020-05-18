package co.com.hotelyando.database.graphqls;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import co.com.hotelyando.database.model.Room;
import co.com.hotelyando.database.repository.IRoomRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {
	
	
	private final IRoomRepository iRoomRepository;
	
	public List<Room> rooms() {
		
		System.out.println(iRoomRepository.findAll().get(0).getDescription());
		
		return iRoomRepository.findAll();
	}
	
	public Room room(String uuid) {
		return iRoomRepository.findByUuid(uuid);
	}
	
}