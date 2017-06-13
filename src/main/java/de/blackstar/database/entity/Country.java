package de.blackstar.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Country.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Country extends AEntity {

    public static final String TABLE_NAME = "country";

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String code;

}
