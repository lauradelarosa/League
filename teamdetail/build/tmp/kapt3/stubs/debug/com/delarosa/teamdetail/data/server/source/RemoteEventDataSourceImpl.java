package com.delarosa.teamdetail.data.server.source;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ*\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\u0006\u0010\u000f\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/delarosa/teamdetail/data/server/source/RemoteEventDataSourceImpl;", "Lcom/delarosa/data/datasource/RemoteEventDataSource;", "eventService", "Lcom/delarosa/teamdetail/data/server/endpoints/EventService;", "(Lcom/delarosa/teamdetail/data/server/endpoints/EventService;)V", "getEvents", "Lcom/delarosa/data/ResultData;", "", "Lcom/delarosa/domain/Event;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "renderData", "resultData", "Lcom/delarosa/teamdetail/data/server/response/EventResponse;", "idTeam", "teamdetail_debug"})
public final class RemoteEventDataSourceImpl implements com.delarosa.data.datasource.RemoteEventDataSource {
    private final com.delarosa.teamdetail.data.server.endpoints.EventService eventService = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getEvents(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.delarosa.data.ResultData<? extends java.util.List<com.delarosa.domain.Event>>> p1) {
        return null;
    }
    
    private final com.delarosa.data.ResultData<java.util.List<com.delarosa.domain.Event>> renderData(com.delarosa.data.ResultData<com.delarosa.teamdetail.data.server.response.EventResponse> resultData, java.lang.String idTeam) {
        return null;
    }
    
    public RemoteEventDataSourceImpl(@org.jetbrains.annotations.NotNull()
    com.delarosa.teamdetail.data.server.endpoints.EventService eventService) {
        super();
    }
}