package ni.dev.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import ni.dev.app.entity.Reader;
import ni.dev.app.repository.ReaderRepository;

@Service
public class ReaderService {

	private ReaderRepository readerRepository;

	public ReaderService(ReaderRepository readerRepository) {
		this.readerRepository = readerRepository;
	}

	public List<Reader> getReaders() {
		return StreamSupport.stream(readerRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Reader getReaderByID(int id) {
		return readerRepository.findById(id).orElse(null);
	}

	public void deleteReader(int id) {
		Optional<Reader> reader = readerRepository.findById(id);
		if (reader.isPresent()) {
			readerRepository.delete(reader.get());
		}
	}
	
	public void editReader(Reader reader) {
		readerRepository.save(reader);
	}
	
	public void createNewReader(Reader reader) {
		readerRepository.save(reader);
	}
	
	public void updateReader(int id, String name) {
		
	}
}
