package com.example.demo.CAS;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@AllArgsConstructor
@ToString
class User{
    String userName;
    int age;

}

/**
 * 解决ABA问题:1.原子应用  2.时间戳原子引用(修改版本号)
 *
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3=new User("z3",22);
        User l4=new User("l4",26);
        AtomicReference<User> atomicReference=new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,l4)+"\t"+atomicReference.get().toString() );
        System.out.println(atomicReference.compareAndSet(z3,l4)+"\t"+atomicReference.get().toString() );
    }
}
