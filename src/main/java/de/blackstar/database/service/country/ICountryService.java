package de.blackstar.database.service.country;

import de.blackstar.database.entity.Country;

public interface ICountryService {

    Country findByCode(final String code);

}
