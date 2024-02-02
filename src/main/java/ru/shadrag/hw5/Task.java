package ru.shadrag.hw5;

import jakarta.persistence.*;

import java.util.Date;


/**
 * ID (автоинкрементное)
 * Описание (не может быть пустым)
 * Статус (одно из значений: "не начата", "в процессе", "завершена")
 * Дата создания (автоматически устанавливается при создании задачи)
 */
@Entity
@Table(name = "tasks")
public class Task {

    public Task() {
        this.status = Status.NOT_START.getDescription();
        this.date = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
