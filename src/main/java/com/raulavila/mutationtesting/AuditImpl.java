package com.raulavila.mutationtesting;

public class AuditImpl implements Audit {
    @Override
    public void register(String request) {
        System.out.println(request);
    }
}
