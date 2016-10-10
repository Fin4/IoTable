package org.iotable.enterprise.session;

import org.iotable.core.model.IoTable;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@Component
@Scope(value= WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IoTableSessionBean {

    private static final Logger LOGGER = Logger.getLogger(IoTableSessionBean.class);

    @PostConstruct
    private void init() {
       LOGGER.info("Session bean created");
    }

    private IoTable ioTable;

    public IoTable getIoTable() {
        return ioTable;
    }

    public void setIoTable(IoTable ioTable) {
        this.ioTable = ioTable;
    }
}
