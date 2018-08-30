package com.example.mapper;

import java.util.List;

import com.example.model.Car;

public interface CarMapper {
	int addCar(Car car);
	int updateCar(int bookid,int userid);
	Car findIdcar(int bookid,int userid);
	List<Car> findAllCar(int userid);
	//void  changeAddCar(int bookid,int userid);
	void changeDelCar(int bookid,int userid);
	void deleteCar(int bookid,int userid);
	void delete(int userid);
}
