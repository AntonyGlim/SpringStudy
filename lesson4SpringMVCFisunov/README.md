**Задание**
 1. Создать сущность «товар» (id, название, стоимость) и соответствующую таблицу в БД. Заполнить таблицу тестовыми данными (20 записей).
 2. Сделать страницу, в которую будут выведены эти записи.
 3. С помощью GET-запроса указывать фильтрацию по:  
 3.1 только минимальной,  
 3.2 только максимальной,  
 3.3 или минимальной и максимальной цене.
 4. *Добавить постраничное отображение (по 5 записей на странице).
   
 **Решение**
 1. В проекте использован SpringBoot
 2. Сконфигурирована таблица:  
```
CREATE TABLE products 
(id bigserial, title varchar(255), cost integer, 
PRIMARY KEY (id)); 
 id |   title   | cost 
----+-----------+------
  1 | milk      |   32
  2 | bread     |   18
  3 | cheese    |   99
  4 | tomato    |   47
  5 | lime      |   32
  6 | butter    |   87
  7 | apple     |   98
  8 | asparagus |   18
  9 | eggs      |   60
 10 | banana    |   44
 11 | beef      |   55
 12 | beet      |   78
 13 | berry     |  200
 14 | biscuits  |  120
 15 | bream     |  150
 16 | cabbage   |   60
 17 | cake      |   35
 18 | carrot    |   78
 19 | cherry    |  250
 20 | sold      |   12
(20 rows)
```  
3. налажена цепочка отображения информации на страницу по схеме: Контроллер -> Сервис -> Репозиторий.
Все продукты отображаются на странице при помощи метода findAll (Spring-Data).

4. Отображение минимального и максимального элемента по цене.
```
/*В контроллере*/
@GetMapping("/minmaxfilter")
public String showProductsWithFilterByMinMaxCost(
        @RequestParam(name = "minPrice") Integer minPrice,
        @RequestParam(name = "maxPrice") Integer maxPrice,
        Model model
){
    if (minPrice == null){
        minPrice = 0;
    }
    if (maxPrice == null){
        maxPrice = Integer.MAX_VALUE;
    }
    List<Product> productsList = productsService.findAllByCostBetween(minPrice, maxPrice);
    model.addAttribute("products", productsList);
    return "products";
}
```

```
/*В файле products.html*/
<table class="table table-hover">
    <thead class="thead-dark">
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Стоимость</th>
    </tr>
    </thead>
    <tbody>
    <tr th:each="prod : ${products}">
        <td th:text="${prod.id}"/>
        <td th:text="${prod.title}"/>
        <td th:text="${prod.cost}"/>
    </tr>
    </tbody>
</table>

<form th:action="@{/minmaxfilter}" method="get">
    <input th:name="minPrice" type="number" placeholder="Минимальная стоимость">
    <input th:name="maxPrice" type="number" placeholder="Максимальная стоимость">
    <button type="submit" class="btn btn-primary">Фильтровать</button>
</form>
```