<%-- 
    Document   : modal
    Created on : 06/06/2018, 01:03:32
    Author     : jonas
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title"%>
<%@attribute name="substitle"%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>

<input class="modal-state" id="modal-1" type="checkbox" checked>
<div class="modal">
    <label class="modal-bg" for="modal-1"></label>
    <div class="modal-body">
        <label class="btn-close" for="modal-1">X</label>
        <h4 class="modal-title">${title}</h4>
        <h5 class="modal-subtitle">${substitle}</h5>
        <p class="modal-text">${message} :D</p>
        <button for="modal-1" class="paper-btn" autofocus="autofocus">Nice!</button>
    </div>
</div>