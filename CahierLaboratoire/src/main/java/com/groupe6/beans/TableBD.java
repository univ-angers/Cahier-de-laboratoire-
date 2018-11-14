package com.groupe6.beans;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface TableBD {
	@Override
	public String toString();
}
