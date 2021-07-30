package me.study.scope.application;

import lombok.RequiredArgsConstructor;
import me.study.scope.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    // private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    public void logic(String id) {
        // MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("Log Service : " + id);
    }
}
