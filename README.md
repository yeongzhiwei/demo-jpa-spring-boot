# Experimenting and understanding Hibernate JPA

Learning points

- implement bidirectional 1-many with `@OneToMany(mappedBy=...)` in Entity A and `@ManyToOne` with `@JoinColumn(name=...)` in Entity B
  - A's primary key is used as foreign key in B
  - `mappedBy` is the variable name in B (not SQL field name)
