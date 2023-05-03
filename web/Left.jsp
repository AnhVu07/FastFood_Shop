<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="card bg-light mb-3">
    <div class="card-header bg-success text-white text-uppercase">Latest product</div>
    <div class="card-body">
        <img class="img-fluid" src="${p.image}" />
        <h4 class="card-title show_txt"><a href="detail?pid=${p.id}" title="View Product">${p.name}</a></h4>
        <p class="card-text">${p.title}</p>
        <p class="bloc_left_price">${p.price}00 đ</p>
    </div>
</div>
