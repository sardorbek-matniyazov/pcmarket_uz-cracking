package basePackage.service;

import basePackage.entity.Message;
import basePackage.payload.MessageDto;
import basePackage.payload.Status;
import basePackage.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record MessageService (MessageRepository repository)
        implements BaseService<MessageDto, Message> {

    @Override
    public List<Message> getAll() {
        return repository.findAll();
    }

    @Override
    public Message get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Status add(MessageDto messageDto) {
        repository.save(
                new Message(
                        messageDto.getName(),
                        messageDto.getEmail(),
                        messageDto.getMessage()
                )
        );
        return Status.builder()
                .active(true)
                .message("Message sent successfully")
                .build();
    }

    @Override
    public Status update(Long id, MessageDto messageDto) {
        if (!repository.existsById(id))
            return Status.builder()
                    .active(false)
                    .message("message does not exist")
                    .build();
        repository.save(
                new Message(
                        id,
                        messageDto.getName(),
                        messageDto.getEmail(),
                        messageDto.getMessage()
                )
        );
        return Status.builder()
                .active(true)
                .message("Message edited successfully")
                .build();
    }

    @Override
    public Status delete(Long id) {
        if (!repository.existsById(id))
            return Status.builder()
                    .active(false)
                    .message("message does not exist")
                    .build();
        repository.deleteById(id);
        return Status.builder()
                .active(true)
                .message("Message deleted successfully")
                .build();
    }
}
