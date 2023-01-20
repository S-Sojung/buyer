package shop.mtcoding.buyer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.buyer.model.Product;
import shop.mtcoding.buyer.model.ProductRepository;
import shop.mtcoding.buyer.model.Purchase;
import shop.mtcoding.buyer.model.PurchaseRepository;

/*
 * @Controller @RestContoller @Mapping @Service @Component
 */

@Service // IoC컨테이너에 등록된다.
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional // 트랜잭션 종료시(메서드 종료시) commit 한다. (트랜잭션 중에서는 아무도 변경못함)
    public int 구매하기(int principalId, int productId, int count) {

        // 2.-> 1 상품 존재 확인, 상품이 존재하면 update 해줘야함.
        // productId가 존재하는지 확인하고 보내줘야함. 클라이언트에게 받는 데이터는 신뢰할 수 없는 데이터..
        Product product = productRepository.findById(productId);
        if (product == null) {
            return -1;
        }
        // 3.-> 2 수량체크
        if (product.getQty() < count) {
            // 실행시에 나는 모든 예외의 부모는 runtime예외 인데 이 오류가 뜨면 @Transaction은 롤백 시켜준다.
            // runtime예외를 터트리는 방법도 있다
            return -1;
        }

        // 4.->3 재고수량 변경
        int result2 = productRepository.updateById(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQty() - count);

        if (result2 != 1) {
            return -1;
        }

        // 1.->4 구매 이력 남기기
        int result = purchaseRepository.insert(principalId, productId, count);
        if (result != 1) {
            // return "redirect:/notfound"; //컨트롤러가 아니라서 못함
            return -1; // return -1 을 하면 오류라는 의미 ,
        }

        // 이 전체를 (DB 관련)비지니스 로직이라 하는데 (일의 최소 단위)트랜잭션 관리가 필요하다. (일이 2개 이상일 경우 )
        // DB를 체크하는것 2,3,4 => 레이어를 따로 만들어 줘야함. (DB의 변경이 있을 때만 .)
        // 서비스라는 레이어 (클래스) : 트랜잭션 관리
        // 무조건 서비스를 호출할 필요는 없다.
        // 서비스 : 여러가지 일이 섞여있이는 일의 최소단위 (트랜잭션 관리) (원자성을 갖추어야한다. )
        // 트랜잭션의 로직 순서도도 중요하다

        return 1;
    } // commit;

    @Transactional
    public int 구매취소하기(int purchaseId) {

        Purchase purchase = purchaseRepository.findById(purchaseId);
        Product p = productRepository.findById(purchase.getProductId());

        // 구매이력 삭제하기
        int result = purchaseRepository.deleteById(purchaseId);
        if (result != 1) {
            return -1;
        }

        // 재고 채워넣기
        int result2 = productRepository.updateById(p.getId(), p.getName(), p.getPrice(),
                p.getQty() + purchase.getCount());
        if (result2 != 1) {
            return -1;
        }

        return 1;
    }
}
