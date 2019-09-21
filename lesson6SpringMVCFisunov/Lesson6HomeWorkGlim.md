in project https://github.com/AntonyGlim/SpringLEDMarket
**ПЕРЕХОД ПО СТРАНИЦАМ СДЕЛАН СЛЕДУЮЩИМ ОБРАЗОМ:**  
Кнопки next и previous не исчизают, но и не дают выйти за границы диапазона   
```
------------------------------part of products.html------------------------------
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <a class="page-link" th:href="@{'/products?' + 'pageNumber=' + ${pageNumber == 1 ? 1 : pageNumber - 1} + ${filters}}">&laquo;</a>
            </li>
            <li class="page-item" th:classappend="${page.getNumber() + 1 == pageNumber ? 'active' : ''}" th:each="pageNumber: ${#numbers.sequence(1, page.getTotalPages(), 1)}">
                <a class="page-link" th:text="${pageNumber}" th:href="@{'/products?' + 'pageNumber=' + ${pageNumber} + ${filters}}"></a>
            </li>
            <li class="page-item">
                <a class="page-link" th:href="@{'/products?' + 'pageNumber=' + ${pageNumber == page.getTotalPages() ? page.getTotalPages() : pageNumber + 1} + ${filters}}">&raquo;</a>
            </li>

        </ul>
    </nav>
```  
**МЕТОДЫ НАСТРОЙКИ ФИЛЬТРОВ ПЕРЕНЕСЕНЫ В СЕРВИС**
```
------------------------------part of ProductsController.java------------------------------
    @GetMapping()
    public String showProducts(
            Model model,
            @RequestParam(name = "word", required = false) String word,
            @RequestParam(name = "min", required = false) Integer min,
            @RequestParam(name = "max", required = false) Integer max,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber)
    {
        productsService.setParams(word, min, max);
        if (pageNumber == null) pageNumber = 1;
        Page<Product> page =
                productsService.findAllByPaginAndFiltering(
                        PageRequest.of(pageNumber - 1, 5, Sort.Direction.ASC, "id"));
        model.addAttribute("page", page);
        model.addAttribute("word", word);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("filters", productsService.getFilterStringForURL());
        return "products";
    }

------------------------------ProductsService.java------------------------------
@Service
public class ProductsService {

    private String word;
    private Integer min;
    private Integer max;
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public void setParams(String word, Integer min, Integer max) {
        this.word = word;
        this.min = min;
        this.max = max;
    }

    public String getFilterStringForURL(){
        StringBuilder stringBuilder = new StringBuilder();
        if (word != null) stringBuilder.append("&word=").append(word);
        if (word != null) stringBuilder.append("&min=").append(min);
        if (word != null) stringBuilder.append("&max=").append(max);
        return stringBuilder.toString();
    }

    public Page<Product> findAllByPaginAndFiltering(Pageable pageable){
        Specification<Product> spec = Specification.where(null);
        if (word != null) spec = spec.and(ProductSpecifications.titleContains(word));
        if (min != null) spec = spec.and(ProductSpecifications.priceGreaterThanOrEq(min));
        if (max != null) spec = spec.and(ProductSpecifications.priceLesserThanOrEq(max));
        return productsRepository.findAll(spec, pageable);
    }

    public Product save(Product product){
        return productsRepository.save(product);
    }

    public Product findById(Long id){
        return productsRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList();
        productsRepository.findAll().forEach(list::add);
        return list;
    }

}

```  
**ДОБАВЛЕНА ВОЗМОЖНОСТЬ ВЫБОРА КОЛИЧЕСТВА СТРАНИЦ**  

```
------------------------------part of products.html------------------------------
    <form th:action="@{/products}" method="GET">
        <input th:value="${productsOnPage != null} ? ${productsOnPage}" th:name="productsOnPage" type="number" placeholder="Страниц на листе">
        <button type="submit" class="btn btn-primary">Выбрать</button>
    </form>

    <!--    НЕ ПОЛУЧИЛОСЬ СДЕЛАТЬ ВЫПАДАЮЩИМ СПИСКОМ:-->
    <!--    <select>-->
    <!--        <option>1</option>-->
    <!--        <option selected>3</option>-->
    <!--        <option>5</option>-->
    <!--        <option>10</option>-->
    <!--        <option>20</option>-->
    <!--    </select>-->

------------------------------part of ProductsController.java------------------------------
    @GetMapping()
    public String showProducts(
            Model model,
            @RequestParam(name = "word", required = false) String word,
            @RequestParam(name = "min", required = false) Integer min,
            @RequestParam(name = "max", required = false) Integer max,
            @RequestParam(name = "productsOnPage", required = false) Integer productsOnPage,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber)
    {
        productsService.setParams(word, min, max, productsOnPage);
        if (productsOnPage == null) productsOnPage = 1;
        if (pageNumber == null) pageNumber = 1;
        Page<Product> page =
                productsService.findAllByPaginAndFiltering(
                        PageRequest.of(pageNumber - 1, productsOnPage, Sort.Direction.ASC, "id"));
        model.addAttribute("page", page);
        model.addAttribute("word", word);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("productsOnPage", productsOnPage);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("filters", productsService.getFilterStringForURL());
        return "products";
    }

------------------------------part of ProductsService.java------------------------------
    public void setParams(String word, Integer min, Integer max, Integer productsOnPage) {
        this.word = word;
        this.min = min;
        this.max = max;
        this.productsOnPage = productsOnPage;
    }

    public String getFilterStringForURL(){
        StringBuilder stringBuilder = new StringBuilder();
        if (word != null) stringBuilder.append("&word=").append(word);
        if (word != null) stringBuilder.append("&min=").append(min);
        if (word != null) stringBuilder.append("&max=").append(max);
        if (productsOnPage != null) stringBuilder.append("&productsOnPage=").append(productsOnPage);
        return stringBuilder.toString();
    }
```  
**ДОБАВЛЕНИЕ ТОВАРА МИНИМАЛЬНЫМИ УСИЛИЯМИ**
```
------------------------------part of products.html------------------------------
<a type="button" class="btn btn-success" th:href="@{'/products/edit'}">add new product</a>

------------------------------part of ProductsController.java------------------------------
    //TODO save filters when add or edit product
    @PostMapping("/edit")
    public String saveModifiedProduct(@ModelAttribute(name = "product") Product product) {
        productsService.save(product);
        return "redirect:/products";
    }

    //TODO save filters when add or edit product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id){
        productsService.deleteById(id);
        return "redirect:/products";
    }
```  
