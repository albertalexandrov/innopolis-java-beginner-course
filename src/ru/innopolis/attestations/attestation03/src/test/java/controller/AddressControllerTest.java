package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.innopolis.Application;
import ru.innopolis.dto.AddressCreateDTO;
import ru.innopolis.dto.AddressUpdateDTO;
import ru.innopolis.dto.PizzaCreateUpdateDTO;
import ru.innopolis.entity.Address;
import ru.innopolis.entity.Pizza;
import ru.innopolis.entity.User;
import ru.innopolis.repository.AddressRepository;
import ru.innopolis.repository.PizzaRepository;
import ru.innopolis.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private AddressRepository addressRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private AddressCreateDTO addressCreateDTO;
    private AddressUpdateDTO addressUpdateDTO;
    private Address address;
    private User user;

    @BeforeEach
    public void setUp() {
        var locality = "г. Казань";
        var street = "ул. Павлюхина";
        var house = "1";
        var porch = "2";
        var floor = "3";
        var apartment = "4";
        user = User
                .builder()
                .id(1L)
                .lastName("Иванов")
                .firstName("Иван")
                .phoneNumber("+79991234567")
                .isDeleted(false)
                .build();
        addressCreateDTO = AddressCreateDTO
                .builder()
                .locality(locality)
                .street(street)
                .house(house)
                .porch(porch)
                .floor(floor)
                .apartment(apartment)
                .userId(user.getId())
                .build();
        addressUpdateDTO = AddressUpdateDTO
                .builder()
                .locality("new " + locality)
                .street("new " + street)
                .house("new " + house)
                .porch("new " + porch)
                .floor("new " + floor)
                .apartment("new " + apartment)
                .build();
        address = Address
                .builder()
                .id(1L)
                .locality(locality)
                .street(street)
                .house(house)
                .porch(porch)
                .floor(floor)
                .apartment(apartment)
                .isDeleted(false)
                .user(user)
                .build();
    }

    @Test
    void createAddress() throws Exception {
        when(userRepository.findFirstByIdAndIsDeleted(user.getId(), false))
                .thenReturn(Optional.of(user));
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        ResultActions response = mockMvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressCreateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.is(address.getId().intValue())))
                .andExpect(jsonPath("$.locality", CoreMatchers.is(address.getLocality())))
                .andExpect(jsonPath("$.street", CoreMatchers.is(address.getStreet())))
                .andExpect(jsonPath("$.house", CoreMatchers.is(address.getHouse())))
                .andExpect(jsonPath("$.porch", CoreMatchers.is(address.getPorch())))
                .andExpect(jsonPath("$.floor", CoreMatchers.is(address.getFloor())))
                .andExpect(jsonPath("$.apartment", CoreMatchers.is(address.getApartment())));

        // пользователь не найден:

        when(userRepository.findFirstByIdAndIsDeleted(user.getId(), false))
                .thenReturn(Optional.empty());
        response = mockMvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressCreateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAddress() throws Exception {
        when(addressRepository.findFirstByIdAndIsDeleted(address.getId(), false))
                .thenReturn(Optional.of(address));
        ResultActions response = mockMvc.perform(get("/address/{id}", address.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(address.getId().intValue())))
                .andExpect(jsonPath("$.locality", CoreMatchers.is(address.getLocality())))
                .andExpect(jsonPath("$.street", CoreMatchers.is(address.getStreet())))
                .andExpect(jsonPath("$.house", CoreMatchers.is(address.getHouse())))
                .andExpect(jsonPath("$.porch", CoreMatchers.is(address.getPorch())))
                .andExpect(jsonPath("$.floor", CoreMatchers.is(address.getFloor())))
                .andExpect(jsonPath("$.apartment", CoreMatchers.is(address.getApartment())))
                .andExpect(jsonPath("$.user.id", CoreMatchers.is(address.getUser().getId().intValue())))
                .andExpect(jsonPath("$.user.firstName", CoreMatchers.is(address.getUser().getFirstName())))
                .andExpect(jsonPath("$.user.lastName", CoreMatchers.is(address.getUser().getLastName())))
                .andExpect(jsonPath("$.user.phoneNumber", CoreMatchers.is(address.getUser().getPhoneNumber())));
    }

    @Test
    void getAddress_AddressNotFound() throws Exception {
        when(addressRepository.findFirstByIdAndIsDeleted(address.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(get("/address/{id}", address.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }

    @Test
    void getAddresses() throws Exception {
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        when(addressRepository.findAllByIsDeleted(false)).thenReturn(addresses);
        ResultActions response = mockMvc.perform(get("/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", CoreMatchers.is(addresses.size())))
                .andExpect(jsonPath("$[0].id", CoreMatchers.is(address.getId().intValue())))
                .andExpect(jsonPath("$[0].locality", CoreMatchers.is(address.getLocality())))
                .andExpect(jsonPath("$[0].street", CoreMatchers.is(address.getStreet())))
                .andExpect(jsonPath("$[0].house", CoreMatchers.is(address.getHouse())))
                .andExpect(jsonPath("$[0].porch", CoreMatchers.is(address.getPorch())))
                .andExpect(jsonPath("$[0].floor", CoreMatchers.is(address.getFloor())))
                .andExpect(jsonPath("$[0].apartment", CoreMatchers.is(address.getApartment())))
                .andExpect(jsonPath("$[0].user.id", CoreMatchers.is(address.getUser().getId().intValue())))
                .andExpect(jsonPath("$[0].user.firstName", CoreMatchers.is(address.getUser().getFirstName())))
                .andExpect(jsonPath("$[0].user.lastName", CoreMatchers.is(address.getUser().getLastName())))
                .andExpect(jsonPath("$[0].user.phoneNumber", CoreMatchers.is(address.getUser().getPhoneNumber())));
        when(addressRepository.findAllByUserIdAndIsDeleted(user.getId(), false)).thenReturn(addresses);
        response = mockMvc.perform(get("/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", user.getId().toString())
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk());
    }

    @Test
    void updatePizza() throws Exception {
        when(addressRepository.findFirstByIdAndIsDeleted(address.getId(), false))
                .thenReturn(Optional.of(address));
        when(addressRepository.save(address)).thenReturn(address);
        ResultActions response = mockMvc.perform(put("/address/{id}", address.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressUpdateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(address.getId().intValue())))
                .andExpect(jsonPath("$.locality", CoreMatchers.is(address.getLocality())))
                .andExpect(jsonPath("$.street", CoreMatchers.is(address.getStreet())))
                .andExpect(jsonPath("$.house", CoreMatchers.is(address.getHouse())))
                .andExpect(jsonPath("$.porch", CoreMatchers.is(address.getPorch())))
                .andExpect(jsonPath("$.floor", CoreMatchers.is(address.getFloor())))
                .andExpect(jsonPath("$.apartment", CoreMatchers.is(address.getApartment())))
                .andExpect(jsonPath("$.user.id", CoreMatchers.is(address.getUser().getId().intValue())))
                .andExpect(jsonPath("$.user.firstName", CoreMatchers.is(address.getUser().getFirstName())))
                .andExpect(jsonPath("$.user.lastName", CoreMatchers.is(address.getUser().getLastName())))
                .andExpect(jsonPath("$.user.phoneNumber", CoreMatchers.is(address.getUser().getPhoneNumber())));
    }

    @Test
    void updateAddress_AddressNotFound() throws Exception {
        when(addressRepository.findFirstByIdAndIsDeleted(address.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(put("/address/{id}", address.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressUpdateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAddress() throws Exception {
        when(addressRepository.findFirstByIdAndIsDeleted(address.getId(), false))
                .thenReturn(Optional.of(address));
        ResultActions response = mockMvc.perform(delete("/address/{id}", address.getId())
                .contentType(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAddress_AddressNotFound() throws Exception {
        when(addressRepository.findFirstByIdAndIsDeleted(address.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(delete("/address/{id}", address.getId())
                .contentType(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }

}
