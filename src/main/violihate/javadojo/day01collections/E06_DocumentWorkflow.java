package main.violihate.javadojo.day01collections;

import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/*
========================================
JAVA DOJO - DAY 01
Exercise 06 - Document Workflow
Difficulty: ⭐⭐⭐⭐
========================================

DESCRIPTION

Simulate a document approval workflow.

AVAILABLE STATES:
- CREATED
- IN_REVIEW
- APPROVED
- REJECTED

IMPLEMENT:
- review()
- approve()
- reject()

ALLOWED TRANSITIONS:
CREATED -> IN_REVIEW
IN_REVIEW -> APPROVED
IN_REVIEW -> REJECTED
REJECTED -> IN_REVIEW

INVALID EXAMPLES:
CREATED -> APPROVED
APPROVED -> CREATED
APPROVED -> REJECTED

REQUIREMENTS

- Invalid transitions must throw a custom exception.
- Keep the implementation clean and extensible.

EXTRA

- Store every state transition inside a history collection.
- Print the workflow history.
*/

class InvalidStateTransitionException extends RuntimeException {
    public InvalidStateTransitionException(E06_DocumentWorkflow.DocumentStatus from, E06_DocumentWorkflow.DocumentStatus to) {
        super("Transition invalid from " + from + " to " + to);
    }
}


public class E06_DocumentWorkflow {

    enum DocumentStatus {
        CREATED,
        IN_REVIEW,
        APPROVED,
        REJECTED
    }
    static class Transition {
        private final DocumentStatus from;
        private final DocumentStatus to;
        private final Instant time;

        public Transition(DocumentStatus from, DocumentStatus to) {
            this.from = from;
            this.to = to;
            this.time = Instant.now();
        }

        @Override
        public String toString() {
            return time.getEpochSecond() + " : " + from + " → " + to;
        }
    }

    static class Document {
    private final Long id;
    private final String title;
    private final String author;
    private DocumentStatus status;
    private final List<Transition> history;

    public Document(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = DocumentStatus.CREATED;
        this.history = new ArrayList<>();
        this.history.add(new Transition(null, DocumentStatus.CREATED));
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public DocumentStatus getStatus() { return status; }
    public List<Transition> getHistory() {
        return new ArrayList<>(history);
    }

    public void review() {
        validateTransition(DocumentStatus.IN_REVIEW);
        this.status = DocumentStatus.IN_REVIEW;
        history.add(new Transition(DocumentStatus.CREATED, DocumentStatus.IN_REVIEW));
    }

    public void approve() {
        validateTransition(DocumentStatus.APPROVED);
        this.status = DocumentStatus.APPROVED;
        history.add(new Transition(DocumentStatus.IN_REVIEW, DocumentStatus.APPROVED));
    }

    public void reject() {
        validateTransition(DocumentStatus.REJECTED);
        this.status = DocumentStatus.REJECTED;
        history.add(new Transition(DocumentStatus.IN_REVIEW, DocumentStatus.REJECTED));
    }

    private void validateTransition(DocumentStatus newStatus) {
        boolean valid = switch (this.status) {
            case CREATED, REJECTED -> newStatus == DocumentStatus.IN_REVIEW;
            case IN_REVIEW -> newStatus == DocumentStatus.APPROVED || newStatus == DocumentStatus.REJECTED;
            case APPROVED -> false;
        };

        if (!valid) {
            throw new InvalidStateTransitionException(this.status, newStatus);
        }
    }

    public void printHistory() {
        System.out.println("Document history '" + title + "':");
        history.forEach(System.out::println);
    }
}
    static class DocumentManager {
        private final List<Document> documents = new ArrayList<>();

        public void addDocument(Document doc) {
            documents.add(doc);
        }

        public Document findById(Long id) {
            return documents.stream()
                    .filter(d -> d.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Document not found"));
        }
    }

    public static void main(String[] args) {
        DocumentManager manager = new DocumentManager();

        Document doc = new Document(1L, "Relazione Annuale 2026", "Mario Rossi");
        manager.addDocument(doc);

        try {
            doc.review();
            Thread.sleep(10);   // just to simulate a bit of time
            doc.approve();

        } catch (InvalidStateTransitionException | InterruptedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        doc.printHistory();
    }
}
