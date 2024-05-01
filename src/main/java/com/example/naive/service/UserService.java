package com.example.naive.service;

import com.example.naive.domain.User;
import com.example.naive.domain.vo.UserUpdate;

import java.util.List;

@SuppressWarnings("all")
public interface UserService {

//    final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMt5fTcRQ1rJGk5uZygx3ZzDfZWPE8+VgN5/0RTiNqMJR0qfqs/dd5Z68/kIsOidvZK4YQfwsrl0eUOheZub+FfWyEzgYwm7ALRxkky5FWdXHoBwoN0va/RkSaNDqbigRAOqamjZkGDucoQqkYWKLT0bJHtUxydGY0uNut3D1PBDAgMBAAECgYAaVugPoytzQB8op1Vb4wCgv2mtXO/KlRGGkUiGieCwFY5l/unvWJLs0Y5tiWN6k+4rZQoigKpneXK31NpXe3GX6icDuHUjPPhZcfT/LSq/SkW5X4KFgdUBOdqD01Fn3/cgjuJqIWTDnGSgOWCPhJRUvt5YQNhaC9lRGOV5ghImOQJBAPEoKJJ5r4glpplUl84O0Boe9iczVCUnLTGyesgzUAOWT4qA1hJHekxXEdcEJYYhpF7XjMwbN2osLm9CLy/oG1cCQQDX/5TDxZK+mfZ4Lb1h5mEsSmBbEuCMgFU/p34qfozcpAgX0Oterdkrj2SWGJQTGPHO2IKwxE/kpLkrwGWv7ar1AkEA0x2h7yEiPuEfKOo6f0TOf5+NRKIVzvSuBuzFuk/Zg1pqRckmHxh+AyjSUFar9vGqsPCorOJCr+r/t5zMLHdYvwJBALniadRRI4iEV2f1dRXbfaTwHRxDkptXlgXBzwIjBnabc5uLYAOK+WpTbOz/Ge+VeAPasB4P0/GhEPpmCRzuHEECQQCo5Opz6OkdgiOm3QrhlwefppqnX4AqyRXozkotcEvpIDJAY+yykO/B6hXzSiYfQ4Cnoe6OQQa8dcjfo3yvOFcN";
//    final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLeX03EUNayRpObmcoMd2cw32VjxPPlYDef9EU4jajCUdKn6rP3XeWevP5CLDonb2SuGEH8LK5dHlDoXmbm/hX1shM4GMJuwC0cZJMuRVnVx6AcKDdL2v0ZEmjQ6m4oEQDqmpo2ZBg7nKEKpGFii09GyR7VMcnRmNLjbrdw9TwQwIDAQAB";

    Object login(User user);

    Object signUp(User user);

    User checkUser(User user);

    User getUserByUid(String uid);

    List<User> getUpdateUsers(List<UserUpdate> uids);

    User updateUser(User user);

    boolean logout();
}
